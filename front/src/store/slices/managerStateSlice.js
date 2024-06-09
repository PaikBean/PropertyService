import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'

export const fetchManagerStateList = createAsyncThunk(
  'common/manager-state-list',
  async () => {
    // const response = await fetch('/api/common/v1/manager-state-list')
    // const data = await response.json()
    const data = {
      responseCode: 'SUCCESS',
      data: [
        {
          mangerStateId: 1,
          managerState: '재직',
        },
        {
          mangerStateId: 2,
          managerState: '휴직',
        },
        {
          mangerStateId: 3,
          managerState: '퇴사',
        },
      ],
      message: null,
      code: '200',
    }
    return data
  }
)

const managerStateSlice = createSlice({
  name: 'managerState',
  initialState: {
    options: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchManagerStateList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchManagerStateList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload.data
      })
      .addCase(fetchManagerStateList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export default managerStateSlice.reducer
