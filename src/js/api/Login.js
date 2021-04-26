import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid) {
    const data = {
        username,
        password,
        code,
        uuid
    }
    return request({
        url: '/user/login',
        method: 'post',
        data: data
    })
}

// 获取用户详细信息
export function getInfo() {
    return request({
        url: '/user/getInfo',
        method: 'get'
    })
}

// 退出方法
export function logout() {
    return request({
        url: '/logout',
        method: 'post'
    })
}

// 获取验证码
export function getCodeImg() {
    return request({
        url: '/user/captchaImage',
        method: 'get'
    })
}

export function regist(data) {
    return request({
        url: '/user/registered',
        method: 'post',
        data: data
    })
}

export function updateD(type, data, userId) {
    return request({
        url: 'user/update/' + type + '/' + userId,
        method: 'put',
        data: data
    })

}

export function updateP(data, userId) {
    return request({
        url: 'user/update/password/' + userId,
        method: 'post',
        data: data
    })

}