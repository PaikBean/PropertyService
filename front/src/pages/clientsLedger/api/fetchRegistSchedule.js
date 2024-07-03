export const fetchRegistSchedule = async (data) => {
  try {
    const requestData = {
      managerId: data.managerId,
      inflowTypeId: data.inflowTypeId,
      clientId: data.clientId,
      scheduleTypeId: data.scheduleTypeId,
      scheduleDate: data.scheduleDate,
      priority: data.priority,
      remark: data.remark,
    }
    console.log(requestData)
    const response = await fetch(`/api/schedule/v1/schedule`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(requestData),
    })
    console.log(response)

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
