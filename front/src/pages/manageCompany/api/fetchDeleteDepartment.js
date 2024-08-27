import { fetchDelete } from '@/utils/fetch/fetchWrapper'
export const fetchDeleteDepartment = async (departmentId) => {
    try {
        const response = await fetchDelete(
            `/api/department/v1/department-info/${departmentId}`, {}
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