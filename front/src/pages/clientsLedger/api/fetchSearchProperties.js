export const fetchSearchProperties = async (data) => {
  try {
    const url = new URL(
      '/api/client/v1/showing-property-list',
      window.location.origin
    )
    const params = {
      addressLevel1Id: data.addressL1,
      addressLevel2Id: data.addressL2,
      propertyTransactionTypeId: data.transactionTypeId,
      propertyTypeId: data.propertyTypeId,
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
