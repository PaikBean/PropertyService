import { fetchPut } from '@/utils/fetch/fetchWrapper'
export const fetchUpdateProperty = async (data) => {
    try {
      const response = await fetchPut(
        '/api/property/v1/property', data
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
