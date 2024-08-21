import { fetchGet } from '@/utils/fetch/fetchWrapper'
export const fetchSearchDepartmentList = async (companyId) => {
    try {
      const response = await fetchGet(
        `/api/department/v1/department-list/${companyId}`, {}, {}
      )
      console.log(response)
      if (response.responseCode === 'SUCCESS') {
        return response
      } else {
        throw new Error(response.message || 'Error!')
      }
    } catch (error) {
      return new Error(error.message) // 에러 발생 시 null 반환
    
  }
}
