import { fetchPut } from '@/utils/fetch/fetchWrapper'
export const fetchUpdateDepartment = async (data) => {
    try {
        const requestData = {
            ...data
        };
      const response = await fetchPut(
        '/api/manager/department/v1/department-info/', requestData
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
