export const fetchSearchDepartmentTotalPrice = async (departmentId, startDate, endDate) => {
    try {
      const response = await fetchGet(
        `/api/department/v1/total-revenue/`, {}, {
            departmentId: departmentId,
            startDate: startDate === "Invalid Date" || startDate === '' ? '19700101' : startDate,
            endDate: endDate === "Invalid Date" || endDate === '' ? new Date().toISOString().slice(0, 10).replace(/-/g, '') : endDate,
        }
      )
      if (response.responseCode === 'SUCCESS') {
        return response
      } else {
        throw new Error(response.message || 'Error!')
      }
    } catch (error) {
      return new Error(error.message) // 에러 발생 시 null 반환
  }
}