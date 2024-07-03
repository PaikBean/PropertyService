const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchScheduleTypeList = createAsyncThunk(
  'common/fetchScheduleTypeList',
  async () => {
    const response = await fetch('/api/schedule/v1/schedule-type-list')
    if (!response.ok) {
      // throw new Error('Network response was not ok')
      throw new Error(response.statusText)
    }
    const result = await response.json()
    return result
  }
)

const scheduleTypeSlice = createSlice({
  name: 'scheduleType',
  initialState: {
    options: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchScheduleTypeList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchScheduleTypeList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload.data
      })
      .addCase(fetchScheduleTypeList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export default scheduleTypeSlice.reducer
