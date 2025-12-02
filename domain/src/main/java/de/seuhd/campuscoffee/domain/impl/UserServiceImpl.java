package de.seuhd.campuscoffee.domain.impl;


import java.util.List;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.ports.UserDataService;
import de.seuhd.campuscoffee.domain.ports.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
        private final UserDataService userDataService;

    @Override
    public void clear() {
        log.warn("Clearing all user data");
        userDataService.clear();
    }

    @Override
    public @NonNull List<User> getAll() {
        log.debug("Retrieving all users");
        return userDataService.getAll();
    }

    @Override
    public @NonNull User getById(@NonNull Long id) {
        log.debug("Retrieving user with ID: {}", id);
        return userDataService.getById(id);
    }

    @Override
    public @NonNull User getByName(@NonNull String loginName) {
        log.debug("Retrieving user with login name: {}", loginName);
        return userDataService.getByLoginName(loginName);

    }

    @Override
    public @NonNull User create(@NonNull User user) {
        log.debug("Creating new user with login name: {}", user.loginName());

        return userDataService.upsert(user);
    }

    @Override
    public @NonNull User update(@NonNull User user) {
        log.debug("Updating user with ID: {}", user.id());

        return userDataService.upsert(user);
    }

    @Override
    public void delete(@NonNull Long id) {
        log.debug("Deleting user with ID: {}", id);
        userDataService.delete(id);
    }
}
