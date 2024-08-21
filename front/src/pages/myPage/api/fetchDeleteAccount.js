import { fetchDelete } from '@/utils/fetch/fetchWrapper'
export const fetchDeleteAccount = async (managerId) => {
    try {
        console.log(managerId)
      const response = await fetchDelete(
        `/api/manager/v1/my-info/${managerId}`, {}
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
