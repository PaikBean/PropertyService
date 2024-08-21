import { fetchGet } from '@/utils/fetch/fetchWrapper'
export const fetchSearchMyInfoInit = async () => {
    try {
      const response = await fetchGet(
        '/api/manager/v1/my-info', {}, {}
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
