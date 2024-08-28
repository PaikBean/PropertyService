import { fetchDelete } from '@/utils/fetch/fetchWrapper'
export const fetchDeleteProperty = async (propertyId) => {
    try {
      const response = await fetchDelete(
        `/api/property/v1/property/${propertyId}`, {}
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
