const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchDepartmentList = createAsyncThunk(
  'company/departmentList',
  async (companyCode) => {
    // const response = await fetch('/api/company/department-list')
    // const data = await response.json()
    const data = [
      {
        departmentId: 1,
        department: 'A 부서',
      },
      {
        departmentId: 2,
        department: 'B 부서',
      },
      {
        departmentId: 3,
        department: 'C 부서',
      },
      {
        departmentId: 4,
        department: 'D 부서',
      },
    ]
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
        state.options = action.payload
      })
      .addCase(fetchDepartmentList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export default departmentSlice.reducer
