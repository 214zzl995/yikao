import request from '@/utils/request'

export function getCourseCommentNumber(CourseId) {
    return request({
        url: '/courseComment/selectNumber/' + CourseId,
        method: 'get'
    })
}

export function getCourseComment(CourseId, query) {
    return request({
        url: '/courseComment/selectComment/' + CourseId,
        method: 'get',
        params: query
    })
}

export function addUserComment(data) {
    return request({
        url: '/courseComment/addComment',
        method: 'post',
        data: data
    })
}