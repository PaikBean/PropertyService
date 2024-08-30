import { fetchPut } from '@/utils/fetch/fetchWrapper'
export const fetchUpdateClient = async (data) => {
    try {
        const requestData = {
            ...data
        };
      const response = await fetchPut(
        '/api/client/v1/client/', requestData
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
