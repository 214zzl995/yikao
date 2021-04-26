import request from '@/utils/request'

export function likes(data) {
    return request({
        url: '/userLike/like',
        method: 'post',
        data: data
    })
}