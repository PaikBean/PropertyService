const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchDepartmentList = createAsyncThunk(
  'manager/fetchDepartmentList',
  async () => {
    // Todo: 회사코드로 등록된 부서들을 검색해서 가져와야함
    // const response = await fetch('/api/client-list')
    // const data = await response.json()
    const data = {
      responseCode: 'SUCCESS',
      data: [
        {
          departmentId: 1,
          departmentName: '부서 1',
        },
        {
          departmentId: 2,
          departmentName: '부서 2',
        },
        {
          departmentId: 2,
          departmentName: '부서 2',
        },
      ],
      message: null,
      code: '200',
    }
    return data
  }
)

const departmentSlice = createSlice({
  name: 'department',
  initialState: {
    options: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchDepartmentList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchDepartmentList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload.data
      })
      .addCase(fetchDepartmentList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export const {} = departmentSlice.actions

export default departmentSlice.reducer
