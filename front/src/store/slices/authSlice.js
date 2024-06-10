import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import login from '@/utils/auth/auth'

export const loginUser = createAsyncThunk(
  'auth/loginUser',
  async ({ email, password }, { rejectWithValue }) => {
    try {
      const response = await login(email, password)
      return response // 서버 응답이 사용자 정보와 JWT 토큰을 포함한다고 가정
    } catch (error) {
      return rejectWithValue(error.response.data)
    }
  }
)

const authSlice = createSlice({
  name: 'auth',
  initialState: {
    user: null,
    token: null,
    status: 'idle',
    error: null,
  },
  reducers: {
    logout: (state) => {
      state.user = null
      state.token = null
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(loginUser.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.user = action.payload.user // 사용자 정보
        state.token = action.payload.token // JWT 토큰
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.payload
      })
  },
})

export const { logout } = authSlice.actions
export default authSlice.reducer
