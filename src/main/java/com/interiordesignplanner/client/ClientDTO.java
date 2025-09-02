package com.interiordesignplanner.client;

import java.util.Map;

public record ClientDTO(Long id,
                String clientName,
                String email,
                String phone,
                String address,
                String notes,
                Map<Long, String> projects) {
}
