const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchTransactionTypeList = createAsyncThunk(
  'transactionType/fetchTransactionTypeList',
  async () => {
    try {
      const response = await fetchGet(
        '/api/property/v1/property-type-list'
      )
      console.log(response)
      if (response.responseCode === 'SUCCESS') {
        return response.data
      } else {
        throw new Error(response.message || 'Error!')
      }
    } catch (error) {
      return new Error(error.message) // 에러 발생 시 null 반환
    }
  }
)

const transactionType = createSlice({
  name: 'transactionType',
  initialState: {
    options: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchTransactionTypeList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchTransactionTypeList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload
      })
      .addCase(fetchTransactionTypeList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export const {} = transactionType.actions

export default transactionType.reducer
