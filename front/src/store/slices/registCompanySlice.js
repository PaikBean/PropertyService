const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchValidBizNumber = createAsyncThunk(
  'signup/fetchValidBizNumber',
  async (validData, { rejectWithValue }) => {
    try {
      //   const response = await fetch(
      //     `/api/common/v1/valid/biz-number-validate?
      //     bNo=${validData.bizNumber}&startDate=${validData.bizStartDate}
      //     &pName=${validData.presidentName}`,
      //     {
      //       method: 'GET',
      //       headers: {
      //         'Content-Type': 'application/json',
      //       },
      //     }
      //   )
      const data = await response.json()
      data = {
        responseCode: 'SUCCESS',
        data: null,
        message: null,
        code: '200',
      }
      if (response.ok) {
        return data
      } else {
        throw new Error(data.message || 'Error!')
      }
    } catch (error) {
      return rejectWithValue(error.message)
    }
  }
)

const registCompanySlice = createSlice({
  name: 'registCompany',
  initialState: {
    validateData: {
      bizNumber: '',
      bizStartDate: '',
      presidentName: '',
      validateResult: false,
      status: 'idle',
      error: null,
    },
    registData: {
      bizNumber: '',
      presidentName: '',
      addressLevel1: null,
      addressLevel2: null,
      addressLevel3: '',
    },
  },
  reducers: {
    setRegistData: (state, action) => {
      state.registData = action.payload
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchValidBizNumber.pending, (state) => {
        state.validateData.status = 'loading'
      })
      .addCase(fetchValidBizNumber.fulfilled, (state, action) => {
        state.validateData.status = 'succeeded'
        state.validateData.validateResult = action.payload.data
        state.validateData.validateResult =
          action.payload.data.responseCode === 'SUCCESS' &&
          action.payload.data.code === '200'
            ? true
            : false
      })
      .addCase(fetchValidBizNumber.rejected, (state, action) => {
        state.validateData.status = 'failed'
        state.validateData.error = action.error.message
      })
  },
})

export const { setRegistData } = registCompanySlice.actions

export default registCompanySlice.reducer
