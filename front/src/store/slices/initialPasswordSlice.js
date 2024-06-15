const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchValidateManager = createAsyncThunk(
  'findEmail/fetchValidateManager',
  async (findData, { rejectWithValue }) => {
    try {
      //   const response = await fetch(
      //     `/api/manager/v1/inital-password/validate?companyCode=${findData.companycode}&email=${findData.email}`
      //   )
      //   const data = await response.json()
      const data = {
        responseCode: 'SUCCESS',
        data: {
          managerId: 1,
        },
        message: null,
        code: '200',
      }
      return data
    } catch (error) {
      return rejectWithValue(error.message)
    }
  }
)
export const fetchInitalPassword = createAsyncThunk(
  'findEmail/fetchInitailPassword',
  async (initalData, { rejectWithValue }) => {
    try {
      //   const response = await fetch(
      //     `/api/manager/v1/inital-password/inital?=${findData.companycode}&email=${findData.email}`
      //   )
      //   const data = await response.json()
      const data = {
        responseCode: 'SUCCESS',
        data: null,
        message: null,
        code: '200',
      }
      return data
    } catch (error) {
      return rejectWithValue(error.message)
    }
  }
)

const initailPassword = createSlice({
  name: 'initailPassword',
  initialState: {
    validateManager: {
      companycode: '',
      email: '',
      status: 'idle',
      error: null,
    },
    initalPassword: {
      managerId: null,
      password: '',
      checkResult: false,
      status: 'idle',
      error: null,
    },
  },
  reducers: {
    setCompanyCode: (state, action) => {
      state.companycode = action.payload
    },
    setEmail: (state, action) => {
      state.email = action.payload
    },
    extraReducer: (builder) => {
      builder
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchValidateManager.pending, (state) => {
        state.validateManager.status = 'loading'
      })
      .addCase(fetchValidateManager.fulfilled, (state, action) => {
        state.validateManager.status = 'succeeded'
        state.initalPassword.managerId = action.payload.data.managerId
      })
      .addCase(fetchValidateManager.rejected, (state, action) => {
        state.validateManager.status = 'failed'
        state.validateManager.error = action.error.message
      })

      .addCase(fetchInitalPassword.pending, (state) => {
        state.initalPassword.status = 'loading'
      })
      .addCase(fetchInitalPassword.fulfilled, (state, action) => {
        state.initalPassword.status = 'succeeded'
      })
      .addCase(fetchInitalPassword.rejected, (state, action) => {
        state.initalPassword.status = 'failed'
        state.initalPassword.error = action.error.message
      })
  },
})

export const { setCompanyCode, setEmail } = initailPassword.actions

export default initailPassword.reducer
