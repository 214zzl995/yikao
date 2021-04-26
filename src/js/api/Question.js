import request from '@/utils/request'

export function CourseQuestion(type, courseId, query) {
    return request({
        url: '/' + type + '/selectByLimit/' + courseId,
        method: 'get',
        params: query
    })
}

export function CourseQuestionNumber(type, courseId) {
    return request({
        url: '/' + type + '/selectByLimitNumber/' + courseId,
        method: 'get',
    })
}

export function CourseQuestionUpdate(type, courseId, data) {
    return request({
        url: '/' + type + '/update/' + courseId,
        method: 'put',
        data: data
    })
}

export function CourseQuestionDelect(type, courseId, data) {
    return request({
        url: '/' + type + '/delete/' + courseId,
        method: 'post',
        data: data
    })
}

export function CourseQuestionInsert(type, courseId, data) {
    return request({
        url: '/' + type + '/insert/' + courseId,
        method: 'post',
        data: data
    })
}

export function addWrongQuestion(data) {
    return request({
        url: '/wrongQuestion/add',
        method: 'post',
        data: data
    })
}

export function WrongQuestionNumber(type) {
    return request({
        url: '/wrongQuestion/selectByLimitNumber/' + type,
        method: 'get',
    })
}

export function WrongQuestion(type, query) {
    return request({
        url: '/wrongQuestion/selectByLimit/' + type,
        method: 'get',
        params: query
    })
}

export function deleteWrongQuestion(id) {
    return request({
        url: '/wrongQuestion/delete/' + id,
        method: 'delete'
    })
}