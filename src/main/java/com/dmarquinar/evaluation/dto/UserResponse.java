package com.dmarquinar.evaluation.dto;

import com.dmarquinar.evaluation.model.Phone;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean isActive;


    public void setIsActive(boolean active) {
        isActive = active;
    }

    public boolean getIsActive() {
        return isActive;
    }
}
