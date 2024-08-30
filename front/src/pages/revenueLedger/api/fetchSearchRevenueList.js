import { fetchGet } from '@/utils/fetch/fetchWrapper'
export const fetchRevenueList = async (data) => {
    try {
      // console.log(data)
      const response = await fetchGet(
        '/api/revenue/v1/revenue-ledger-list', {}, data
      )
      // console.log(response)
      if (response.responseCode === 'SUCCESS') {
        return response
      } else {
        throw new Error(response.message || 'Error!')
      }
    } catch (error) {
      return new Error(error.message) // 에러 발생 시 null 반환
    
  }
}
