import { fetchPost } from '@/utils/fetch/fetchWrapper'

export const fetchRegistRevenue = async (registData) => {
  try {
    // const requestData = {
    //   managerId: data.managerId,
    //   ownerName: data.ownerName,
    //   clientName: data.clientName,
    //   addressL1: data.addressL1,
    //   addressL2: data.addressL2,
    //   addressL3: data.addressL3,
    //   contractStartDate: data.startDate,
    //   contractEndDate: data.endDate,
    //   transactionTypeId: data.transaction.transactionType,
    //   deposit: data.transaction.deposit,
    //   monthlyFee: data.transaction.monthlyFee,
    //   jeonseFee: data.transaction.jeonseFee,
    //   tradeFee: data.transaction.tradeFee,
    //   commission: data.commision,
    //   remark: data.remark,
    // }
    const requestData = {
      managerId: registData.managerId,
      ownerName: registData.ownerName,
      clientName: registData.clientName,
      addressL1: registData.addressL1,
      addressL2: registData.addressL2,
      addressL3: registData.addressL3,
      contractStartDate: registData.startDate,
      contractEndDate: registData.endDate,
      deposit: registData.transaction.deposit,
      monthlyFee: registData.transaction.monthlyFee,
      jeonseFee: registData.transaction.jeonseFee,
      tradeFee: registData.transaction.tradeFee,
      commission: registData.commision,
      remark: registData.remark,
      transactionType: (() => {
        switch (registData.transaction.transactionType) {
          case 1:
            return 'MONTHLY';
          case 2:
            return 'JEONSE';
          case 3:
            return 'TRADE';
          case 4:
            return 'SHORTERM';
          default:
            return registData.transaction.transactionType; // 해당되지 않는 경우 원래 값 유지
        }
      })()
    }

    const result = await fetchPost(
      '/api/revenue/v1/revenue-ledger',
      requestData
    )
    // console.log(response)
    // const result = await response.json
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
