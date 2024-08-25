import { fetchGet } from '@/utils/fetch/fetchWrapper'
export const fetchSearchDepartmentMemeberListInModal = async (companyId) => {
    try {
      const response = await fetchGet(
        `/api/company/v1/manager-list/${companyId}`, {}, {}
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
    