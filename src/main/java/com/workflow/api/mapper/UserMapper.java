package com.workflow.api.mapper;

import com.workflow.api.dto.user.UserSummaryResponse;
import com.workflow.api.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserSummaryResponse toSummaryResponse(User user) {
        if (user == null) return null;

        return new UserSummaryResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public List<UserSummaryResponse> toSummaryResponseList(List<User> users) {
        if (users == null) {
            return List.of();
        }

        return users.stream()
                .map(this::toSummaryResponse)
                .collect(Collectors.toList());
    }
}
