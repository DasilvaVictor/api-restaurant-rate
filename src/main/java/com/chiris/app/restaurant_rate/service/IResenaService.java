package com.chiris.app.restaurant_rate.service;

import com.chiris.app.restaurant_rate.dto.ResenaDTO;
import com.chiris.app.restaurant_rate.dto.ResenaUpdateRequestDTO;

public interface IResenaService {
    ResenaDTO createResena(ResenaDTO resena);
    ResenaDTO updateResena(Long id, Long userId, ResenaUpdateRequestDTO request);
    void deleteResena(Long id, Long userId);
}
