export const fetchRevenueList = async (data) => {
  try {
    const url = new URL(
      '/api/revenue/v1/revenue-ledger-list',
      window.location.origin
    )
    const params = {
      managerId: data.managerId,
      addressL1Id: data.addressL1,
      addressL2Id: data.addressL2,
      contractStartDate: data.startDate,
      contractEndDate: data.endDate,
      transactionType: data.transactionTypeId,
    }

    Object.keys(params).forEach((key) => {
      if (params[key] !== null && params[key] !== '') {
        url.searchParams.append(key, params[key])
      }
    })

    console.log(url.toString()) // URL을 로그로 출력하여 확인
    const response = await fetch(url, {
      method: 'GET',
    })
    if (!response.ok) {
      // throw new Error('Network response was not ok')
      throw new Error(response.statusText)
    }
    const result = await response.json()
    return result
  } catch (error) {
    console.error('Error saving revenue data:', error)
    throw error
  }
}
