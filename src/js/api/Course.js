import request from '@/utils/request'

export function getUserCourse() {
    return request({
        url: '/myCourse/getUserCourse',
        method: 'get'
    })
}



export function exitUserCourse(query) {

    return request({
        url: '/myCourse/exitCourse',
        method: 'delete',
        params: query
    })
}

export function selectCourse(query) {

    return request({
        url: '/course/selectCourse',
        method: 'get',
        params: query
    })
}

export function addUserCourse(Course) {

    return request({
        url: '/myCourse/addUserCourse',
        method: 'post',
        data: Course
    })
}

export function getCourse(CourseId) {

    return request({
        url: '/course/getCourse/' + CourseId,
        method: 'get',
    })
}


export function updateCourse(CourseId, data) {

    return request({
        url: '/course/update/' + CourseId,
        method: 'put',
        data: data
    })

}

export function getCourseUser(CourseId) {

    return request({
        url: '/course/getCourseUser/' + CourseId,
        method: 'get',
    })

}

export function exitStu(data, CourseId) {

    return request({
        url: '/myCourse/exitStu/' + CourseId,
        method: 'post',
        data: data
    })

}

export function addCourse(data) {
    return request({
        url: '/course/addCourse',
        method: 'post',
        data: data
    })

}

export function getIndexCourse() {
    return request({
        url: '/course/index',
        method: 'get',
    })
}