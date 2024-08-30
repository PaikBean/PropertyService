import { fetchPut } from '@/utils/fetch/fetchWrapper'
export const fetchUpdateMyInfo = async (data) => {
    try {
        const requestData = {
            managerId: data.managerId,
            managerName: data.managerName,
            managerGender: data.managerGender === "남성" ? "MALE" : data.managerGender === "여성" ? "FEMALE" : "UNKNOWN",
            managerPhoneNumber: data.managerPhoneNumber,
            managerPosition: data.managerPosition,
            managerRank: data.managerRank
        };
      const response = await fetchPut(
        '/api/manager/v1/my-info', requestData
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
