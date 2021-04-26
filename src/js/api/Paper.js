import request from '@/utils/request'

export function getPaper(PaperId) {
    return request({
        url: '/paper/getPaper/' + PaperId,
        method: 'get'
    })
}

export function update(PaperId, data) {
    return request({
        url: '/paper/update/' + PaperId,
        method: 'put',
        data: data
    })
}

export function Delete(PaperId) {
    return request({
        url: '/paper/delete/' + PaperId,
        method: 'delete',
    })
}

export function addPaper(Paper) {
    return request({
        url: '/paper/addPaper',
        method: 'post',
        data: Paper
    })
}

export function InitExamination(PaperId) {
    return request({
        url: '/paper/InitExamination/' + PaperId,
        method: 'get',
    })
}

export function getExamination(PaperId) {
    return request({
        url: '/paper/Examination/' + PaperId,
        method: 'get'
    })
}

export function assignment(paperId, data) {
    return request({
        url: '/paperHistory/assignment/' + paperId,
        method: 'post',
        data: data
    })
}

export function getUserPaperHistory() {
    return request({
        url: '/paperHistory/getUserPaperHistory',
        method: 'get'
    })
}


export function getPaperHistoryData(id) {
    return request({
        url: '/paperHistory/getPaperHistoryData/' + id,
        method: 'get'
    })
}