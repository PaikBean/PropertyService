import { fetchPut } from '@/utils/fetch/fetchWrapper'
export const fetchUpdateBuilding = async (data) => {
    try {
        const requestData = {
            ...data,
            addressLevel1: data.buildingAddressLevel1,
            addressLevel2: data.buildingAddressLevel2,
            addressLevel3: data.buildingAddressLevel3
        }
      const response = await fetchPut(
        '/api/building/v1/building-info', requestData
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
