package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.common.annotation.Business;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.convertor.UserConvertor;
import org.delivery.api.domain.user.service.UserService;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConvertor userConvertor;
    private final TokenBusiness tokenBusiness;

    /**
     * 사용자에 대한 가입 처리
     * 1. request -> entity
     * 2. entity -> save
     * 3. save Entity -> response
     * return response
     * @param request
     * @return
     */
    public UserResponse register(UserRegisterRequest request) {
        var entity = userConvertor.toEntity(request);
        var savedEntity = userService.register(entity);
        var response = userConvertor.toResponse(savedEntity);
        return response;

      /*  return Optional.ofNullable(request)
                .map(userConvertor::toEntity)
                .map(userService::register)
                .map(userConvertor::toResponse)
                .orElseThrow(() -> new RuntimeException("UserRegisterRequest is null"));*/
    }


    /**
     * 1. email, password 를 가지고 사용자 체크
     * 2. storeuser entity 로그인 확인
     * 3. token 생성
     * 4. token response
     * @param request
     */
    public TokenResponse login(UserLoginRequest request) {

        var userEntity = userService.login(request.getEmail(), request.getPassword());
        // 사용자가 없으면 throw

        // TODO 토큰 생성 로직으로 변경하기
        var userId = userEntity.getId();
        var tokenResponse = tokenBusiness.issueToken(userEntity);

        return tokenResponse;
    }

    public UserResponse me(Long userId) {
        var userEntity = userService.getUserWithThrow(userId);
        var response = userConvertor.toResponse(userEntity);
        return response;
    }
}
