import { fetchPost } from '@/utils/fetch/fetchWrapper'
export const fetchCreateDepartment = async (requestData) => {
    try {
      const response = await fetchPost(
        `/api/department/v1/department/`, requestData
      )
      if (response.responseCode === 'SUCCESS') {
        return response
      } else {
        throw new Error(response.message || 'Error!')
      }
    } catch (error) {
      return new Error(error.message) // 에러 발생 시 null 반환
  }
}
