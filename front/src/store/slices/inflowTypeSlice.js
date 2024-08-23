const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchInflowTypeList = createAsyncThunk(
  'common/fetchinflowTypeList',
  async () => {
    const response = await fetch('/api/client/v1/inflow-type-list')
    if (!response.ok) {
      // throw new Error('Network response was not ok')
      throw new Error(response.statusText)
    }
    const result = await response.json()
    console.log(result)
    return result
  }
)

const inflowTypeSlice = createSlice({
  name: 'inflowType',
  initialState: {
    options: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchInflowTypeList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchInflowTypeList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload.data
      })
      .addCase(fetchInflowTypeList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export default inflowTypeSlice.reducer
