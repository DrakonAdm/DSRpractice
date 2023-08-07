package com.csa.app.dto.auth;

import com.csa.app.dto.response.PersonProfileDto;
import com.fasterxml.jackson.annotation.JsonProperty;


public record LoginResponse(

        PersonProfileDto personProfileDto,

        String token

) {
}
