const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchClientList = createAsyncThunk(
  'manager/fetchClientList',
  async () => {
    // Todo: 담당 매니저에 등록된 고객들로 검색해서 가져와야함
    // const response = await fetch('/api/client-list')
    // const data = await response.json()
    const data = {
      responseCode: 'SUCCESS',
      data: [
        {
          clientId: 1,
          clientName: '고객 1',
        },
        {
          clientId: 2,
          clientName: '고객 2',
        },
        {
          clientId: 3,
          clientName: '고객 3',
        },
        {
          clientId: 4,
          clientName: '고객 4',
        },
      ],
      message: null,
      code: '200',
    }
    return data
  }
)

const clientSlice = createSlice({
  name: 'client',
  initialState: {
    options: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchClientList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchClientList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload.data
      })
      .addCase(fetchClientList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export const {} = clientSlice.actions

export default clientSlice.reducer
