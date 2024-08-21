import { fetchGet } from '@/utils/fetch/fetchWrapper'
export const fetchSearchDepartment = async (departmentId) => {
    try {
      const response = await fetchGet(
        `/api/department/v1/department-info/${departmentId}`, {}, {}
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
