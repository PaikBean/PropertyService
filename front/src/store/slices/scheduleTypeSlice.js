const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchScheduleTypeList = createAsyncThunk(
  'common/fetchScheduleTypeList',
  async () => {
    const response = await fetch('/api/schedule/v1/schedule-type-list')
    const data = await response.json()
    return data
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
