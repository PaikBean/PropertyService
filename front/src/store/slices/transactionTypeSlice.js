const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchTransactionTypeList = createAsyncThunk(
  'transactionType/fetchTransactionTypeList',
  async () => {
    const response = await fetch('/api/common//v1/transaction-type-list')
    const data = await response.json()
    return data
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
        state.options = action.payload.data
      })
      .addCase(fetchTransactionTypeList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export const {} = transactionType.actions

export default transactionType.reducer
