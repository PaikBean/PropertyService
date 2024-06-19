const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchManagerList = createAsyncThunk(
  'manager/fetchManagerList',
  async () => {
    // const response = await fetch('/api/manager-list')
    // const data = await response.json()
    const data = {
      responseCode: 'SUCCESS',
      data: [
        {
          managerId: 1,
          managerName: '홍길동',
        },
        {
          managerId: 2,
          managerName: '김길동',
        },
        {
          managerId: 3,
          managerName: '이길동',
        },
        {
          managerId: 4,
          managerName: '연길동',
        },
        {
          managerId: 5,
          managerName: '최길동',
        },
        {
          managerId: 6,
          managerName: '박길동',
        },
        {
          managerId: 7,
          managerName: '황길동',
        },
      ],
      message: null,
      code: '200',
    }
    return data
  }
)

const managerSlice = createSlice({
  name: 'manager',
  initialState: {
    options: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchManagerList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchManagerList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload.data
      })
      .addCase(fetchManagerList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export const {} = managerSlice.actions

export default managerSlice.reducer
