import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import VueSetupExtend from 'vite-plugin-vue-setup-extend'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
      vue(),
      VueSetupExtend(),
  ],
  resolve: {
    alias: {
      '@': path.resolve(__dirname,'./src')
    }
  }
})
