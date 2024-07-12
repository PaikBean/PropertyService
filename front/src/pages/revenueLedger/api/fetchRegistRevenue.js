import { fetchPost } from '@/utils/fetch/fetchWrapper'

export const fetchRegistRevenue = async (data) => {
  try {
    const requestData = {
      managerId: data.managerId,
      ownerName: data.ownerName,
      clientName: data.clientName,
      addressL1: data.addressL1,
      addressL2: data.addressL2,
      addressL3: data.addressL3,
      contractStartDate: data.startDate,
      contractEndDate: data.endDate,
      transactionTypeId: data.transaction.transactionType,
      deposit: data.transaction.deposit,
      monthlyFee: data.transaction.monthlyFee,
      jeonseFee: data.transaction.jeonseFee,
      tradeFee: data.transaction.tradeFee,
      commission: data.commision,
      remark: data.remark,
    }

    const response = await fetchPost(
      '/api/revenue/v1/revenue-ledger',
      requestData
    )
    const result = await response.json()
    if (result.responseCode === 'SUCCESS') {
      return result
    } else {
      throw new Error(result.message)
    }
  } catch (error) {
    console.error('Error saving revenue data:', error)
    throw error
  }
}
