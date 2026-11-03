import type { Metadata, Viewport } from "next";
import "./globals.css";
export const metadata: Metadata = { title:"ChatGPT Polska — Personal Edition", description:"Polskie centrum dostępu do ChatGPT i narzędzi AI.", applicationName:"ChatGPT Polska", manifest:"/manifest.webmanifest", icons:{icon:"/icon.svg",apple:"/icon.svg"}, appleWebApp:{capable:true,statusBarStyle:"black-translucent",title:"ChatGPT Polska"} };
export const viewport: Viewport = {themeColor:"#070914",width:"device-width",initialScale:1};
export default function RootLayout({children}:{children:React.ReactNode}) { return <html lang="pl"><body>{children}</body></html>; }