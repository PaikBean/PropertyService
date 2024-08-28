import { fetchPut } from '@/utils/fetch/fetchWrapper'
export const fetchUpdateSchedule = async (data) => {
    try {
      const response = await fetchPut(
        '/api/schedule/v1/schedule/', data
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
