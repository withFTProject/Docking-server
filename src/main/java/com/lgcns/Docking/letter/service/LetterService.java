package com.lgcns.Docking.letter.service;

import com.lgcns.Docking.letter.dto.LetterCreateDTO;
import com.lgcns.Docking.letter.dto.LetterEditDTO;
import com.lgcns.Docking.letter.dto.LetterReadResponseDTO;
import com.lgcns.Docking.letter.entity.Letter;
import com.lgcns.Docking.letter.entity.LetterRepository;
import com.lgcns.Docking.planet.dto.PlanetListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lgcns.Docking.user.repository.UserRepository;
import com.lgcns.Docking.user.entity.User;

@Service
@RequiredArgsConstructor
@Transactional
public class LetterService {

    private final LetterRepository letterRepository;
    private final UserRepository userRepository;

    // 편지 생성
    public Letter createLetter(LetterCreateDTO dto, String username) {
        Letter letter = new Letter();
        letter.setTitle(dto.getTitle());
        letter.setDescription(dto.getDescription());

        // 사용자 정보 설정
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setName("카카오 사용자");
            userRepository.save(user);
        }
        letter.setUser(user);

        // 저장 후 반환 (여기서 ID가 생성됨)
        return letterRepository.save(letter);
    }

    // 편지 조회
    @Transactional(readOnly = true)
    public LetterReadResponseDTO getLetter(Long id) {
        Letter letter = letterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 편지가 없습니다."));

        return new LetterReadResponseDTO(
                letter.getTitle(),
                letter.getDescription()
        );
    }

    // 편지 수정
    public Letter editLetter(Long id, LetterEditDTO dto) {
        Letter letter = letterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 편지가 없습니다."));

        letter.setTitle(dto.getTitle());
        letter.setDescription(dto.getDescription());

        return letterRepository.save(letter);
    }

    // 편지 삭제
    public void deleteLetter(Long id) {
        letterRepository.deleteById(id);
    }

    // 행성 저장 (기존 planet 패키지의 기능)
    public Letter savePlanetToLetter(Long letterId, String planetUrl) {
        Letter letter = letterRepository.findById(letterId)
                .orElseThrow(() -> new IllegalArgumentException("편지를 찾을 수 없습니다."));

        letter.setPlanet(planetUrl);
        return letterRepository.save(letter);
    }

    // 행성 목록 조회 (기존 planet 패키지의 기능)
    @Transactional(readOnly = true)
    public Page<PlanetListResponseDto> getPlanetList(int page, int size) {
        Page<Letter> letters = letterRepository.findAll(PageRequest.of(page, size));
        return letters.map(letter -> new PlanetListResponseDto(letter.getPlanet()));
    }
}