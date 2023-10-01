USE [ProjectHK4]
GO
/****** Object:  Table [dbo].[admin]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[admin](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](250) NULL,
	[password] [varchar](250) NULL,
	[email] [varchar](250) NULL,
	[role] [varchar](50) NULL,
 CONSTRAINT [PK_admin] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[appointment]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[appointment](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[starttime] [time](0) NULL,
	[endtime] [time](0) NULL,
	[symptom] [varchar](250) NULL,
	[type_payment] [varchar](250) NULL,
	[price] [int] NULL,
	[doctor_id] [int] NULL,
	[patient_id] [int] NULL,
	[date] [date] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_appointment] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[casher]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[casher](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](250) NULL,
	[dob] [datetime] NULL,
	[password] [varchar](250) NULL,
	[email] [varchar](250) NULL,
	[address] [varchar](250) NULL,
	[role] [varchar](50) NULL,
	[phone] [varchar](250) NULL,
	[create_at] [datetime] NULL,
	[gender] [bit] NULL,
 CONSTRAINT [PK_casher] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[company]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[company](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](250) NULL,
 CONSTRAINT [PK_company] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[doctor]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[doctor](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](250) NULL,
	[username] [varchar](250) NULL,
	[password] [varchar](250) NULL,
	[email] [varchar](250) NULL,
	[address] [varchar](250) NULL,
	[gender] [bit] NULL,
	[type_doctor_id] [int] NULL,
	[create_at] [datetime] NULL,
	[image] [varchar](250) NULL,
	[role] [varchar](50) NULL,
 CONSTRAINT [PK_doctor] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[donthuoc]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[donthuoc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[total_money] [int] NOT NULL,
	[casher_id] [int] NULL,
	[create_at] [datetime] NULL,
	[toathuoc_id] [int] NULL,
 CONSTRAINT [PK_donthuo] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[donthuoc_details]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[donthuoc_details](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[donthuoc_id] [int] NULL,
	[thuoc_id] [int] NULL,
	[quantity] [int] NULL,
	[price] [int] NULL,
 CONSTRAINT [PK_donthuoc_details] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[donvi]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[donvi](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](250) NOT NULL,
 CONSTRAINT [PK_donvi] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[feedback]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[feedback](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](250) NULL,
	[content] [varchar](250) NULL,
	[email] [varchar](250) NULL,
	[status] [bit] NULL,
	[create_at] [datetime] NULL,
	[phone] [varchar](250) NULL,
 CONSTRAINT [PK_feedback] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lichlamviec]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lichlamviec](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[thu] [varchar](250) NULL,
	[date] [date] NULL,
	[doctor_id] [int] NULL,
	[create_at] [datetime] NULL,
	[starttime] [time](0) NULL,
	[endtime] [time](0) NULL,
 CONSTRAINT [PK_Table_1] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[news]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[news](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](250) NOT NULL,
	[content] [text] NULL,
	[status] [bit] NULL,
	[create_at] [datetime] NULL,
	[image] [varchar](250) NULL,
 CONSTRAINT [PK_news] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[patient]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[patient](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[password] [varchar](250) NULL,
	[name] [varchar](250) NULL,
	[email] [varchar](250) NULL,
	[address] [varchar](250) NULL,
	[phone] [varchar](50) NULL,
	[image] [varchar](250) NULL,
	[role] [varchar](50) NULL,
	[gender] [bit] NULL,
	[dob] [datetime] NULL,
 CONSTRAINT [PK_patient] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rating]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rating](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[doctor_id] [int] NULL,
	[patient_id] [int] NULL,
	[rating] [decimal](2, 1) NULL,
	[comment] [varchar](250) NULL,
	[create_at] [datetime] NULL,
 CONSTRAINT [PK_rating] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[taophieukham]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[taophieukham](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[sothutu] [int] NULL,
	[name] [varchar](250) NULL,
	[phone] [varchar](250) NULL,
	[address] [varchar](250) NULL,
	[total_money] [int] NULL,
	[casher_id] [int] NULL,
	[type_doctor_id] [int] NULL,
	[create_at] [datetime] NULL,
	[gender] [bit] NULL,
	[dob] [datetime] NULL,
	[sympton] [varchar](250) NULL,
 CONSTRAINT [PK_taophieukham] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[thuoc]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[thuoc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](250) NULL,
	[composition] [varchar](250) NULL,
	[quantity] [int] NULL,
	[price] [int] NULL,
	[typethuoc_id] [int] NULL,
	[create_at] [datetime] NULL,
	[manufacturing_date] [date] NULL,
	[expiry_date] [date] NULL,
	[company_id] [int] NULL,
	[donvi_id] [int] NULL,
 CONSTRAINT [PK_thuoc] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[toathuoc]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[toathuoc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[doctor_id] [int] NULL,
	[create_at] [datetime] NULL,
	[sympton] [varchar](250) NULL,
	[taophieukham_id] [int] NULL,
	[ngaytaikham] [datetime] NULL,
 CONSTRAINT [PK_toathuoc] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[toathuoc_details]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[toathuoc_details](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[sang] [varchar](250) NULL,
	[trua] [varchar](250) NULL,
	[chieu] [varchar](250) NULL,
	[toi] [varchar](250) NULL,
	[quantity] [int] NULL,
	[toathuoc_id] [int] NULL,
	[thuoc_id] [int] NULL,
 CONSTRAINT [PK_toathuoc_details] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[type_doctor]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[type_doctor](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](150) NULL,
 CONSTRAINT [PK_type_doctor] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[typethuoc]    Script Date: 10/2/2023 12:18:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[typethuoc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](250) NULL,
 CONSTRAINT [PK_typethuoc] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[admin] ON

INSERT [dbo].[admin] ([id], [username], [password], [email], [role]) VALUES (1, N'thuong admin', N'$2y$10$PXc/yMXBO0o4tFOGRjNZquyKlbCuNgdsNJUozNIwNnds6i84Va8du', N'adminA@gmail.com', N'ADMIN')
SET IDENTITY_INSERT [dbo].[admin] OFF
GO
SET IDENTITY_INSERT [dbo].[appointment] ON

INSERT [dbo].[appointment] ([id], [starttime], [endtime], [symptom], [type_payment], [price], [doctor_id], [patient_id], [date], [status]) VALUES (2, CAST(N'07:00:00' AS Time), CAST(N'07:30:00' AS Time), N'béophì', N'ONLINE', 50, 1, 1, CAST(N'2023-09-28' AS Date), 1)
INSERT [dbo].[appointment] ([id], [starttime], [endtime], [symptom], [type_payment], [price], [doctor_id], [patient_id], [date], [status]) VALUES (3, CAST(N'12:00:00' AS Time), CAST(N'12:30:00' AS Time), N'smui', N'ONLINE', 50, 1, 1, CAST(N'2023-09-29' AS Date), 0)
INSERT [dbo].[appointment] ([id], [starttime], [endtime], [symptom], [type_payment], [price], [doctor_id], [patient_id], [date], [status]) VALUES (4, CAST(N'13:00:00' AS Time), CAST(N'13:30:00' AS Time), N'm-t', N'ONLINE', 50, 1, 1, CAST(N'2023-09-29' AS Date), 0)
INSERT [dbo].[appointment] ([id], [starttime], [endtime], [symptom], [type_payment], [price], [doctor_id], [patient_id], [date], [status]) VALUES (1002, CAST(N'07:00:00' AS Time), CAST(N'07:30:00' AS Time), N'cm', N'ONLINE', 50, 3, 1, CAST(N'2023-09-30' AS Date), 0)
INSERT [dbo].[appointment] ([id], [starttime], [endtime], [symptom], [type_payment], [price], [doctor_id], [patient_id], [date], [status]) VALUES (1003, CAST(N'12:30:00' AS Time), CAST(N'13:00:00' AS Time), N's-t', N'ONLINE', 50, 3, 1, CAST(N'2023-09-30' AS Date), 0)
INSERT [dbo].[appointment] ([id], [starttime], [endtime], [symptom], [type_payment], [price], [doctor_id], [patient_id], [date], [status]) VALUES (1004, CAST(N'17:00:00' AS Time), CAST(N'17:30:00' AS Time), N'uoi', N'ONLINE', 50, 4, 1, CAST(N'2023-10-02' AS Date), 0)
INSERT [dbo].[appointment] ([id], [starttime], [endtime], [symptom], [type_payment], [price], [doctor_id], [patient_id], [date], [status]) VALUES (1005, CAST(N'18:00:00' AS Time), CAST(N'18:30:00' AS Time), N'say-n-ng', N'ONLINE', 50, 4, 1, CAST(N'2023-10-02' AS Date), 0)
INSERT [dbo].[appointment] ([id], [starttime], [endtime], [symptom], [type_payment], [price], [doctor_id], [patient_id], [date], [status]) VALUES (1006, CAST(N'12:00:00' AS Time), CAST(N'12:30:00' AS Time), N'chóngmt', N'ONLINE', 50, 3, 1, CAST(N'2023-10-01' AS Date), 0)
INSERT [dbo].[appointment] ([id], [starttime], [endtime], [symptom], [type_payment], [price], [doctor_id], [patient_id], [date], [status]) VALUES (2002, CAST(N'11:30:00' AS Time), CAST(N'12:00:00' AS Time), N'daubng', N'ONLINE', 50, 1, 1, CAST(N'2023-10-03' AS Date), 0)
SET IDENTITY_INSERT [dbo].[appointment] OFF
GO
SET IDENTITY_INSERT [dbo].[casher] ON

INSERT [dbo].[casher] ([id], [name], [dob], [password], [email], [address], [role], [phone], [create_at], [gender]) VALUES (1, N'thuong casher', CAST(N'2003-06-18T00:00:00.000' AS DateTime), N'$2a$10$tPkEM36ZrWn3ka6yRyYUPeLTF9YGidCYzxJMxdhMGHWq1haOn5H4.', N'casherA@gmail.com', N'HCM city, VietNam', N'CASHER', N'0123456789', CAST(N'2023-09-30T20:03:48.630' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[casher] OFF
GO
SET IDENTITY_INSERT [dbo].[company] ON

INSERT [dbo].[company] ([id], [name]) VALUES (1, N'FPT Play')
SET IDENTITY_INSERT [dbo].[company] OFF
GO
SET IDENTITY_INSERT [dbo].[doctor] ON

INSERT [dbo].[doctor] ([id], [name], [username], [password], [email], [address], [gender], [type_doctor_id], [create_at], [image], [role]) VALUES (1, N'thuong doctor', N'thuong doctor', N'$2y$10$PXc/yMXBO0o4tFOGRjNZquyKlbCuNgdsNJUozNIwNnds6i84Va8du', N'doctorA@gmail.com', N'SaiGon', 1, 1, CAST(N'2023-09-28T04:20:55.677' AS DateTime), N'https://firebasestorage.googleapis.com/v0/b/projecthk4chap2.appspot.com/o/doctors%2Fnull%2Fdoctor01.jpg?alt=media', N'DOCTOR')
INSERT [dbo].[doctor] ([id], [name], [username], [password], [email], [address], [gender], [type_doctor_id], [create_at], [image], [role]) VALUES (2, N'doctor thuong', N'doctor thuong', N'$2y$10$PXc/yMXBO0o4tFOGRjNZquyKlbCuNgdsNJUozNIwNnds6i84Va8du', N'doctorB@gmail.com', N'HaNoi', 1, 1, CAST(N'2023-09-28T04:20:55.677' AS DateTime), N'https://firebasestorage.googleapis.com/v0/b/projecthk4chap2.appspot.com/o/doctors%2Fnull%2Fdoctor04.jpg?alt=media', N'DOCTOR')
INSERT [dbo].[doctor] ([id], [name], [username], [password], [email], [address], [gender], [type_doctor_id], [create_at], [image], [role]) VALUES (3, N'Nguyen Van A', N'A Nguyen', N'$10$PXc/yMXBO0o4tFOGRjNZquyKlbCuNgdsNJUozNIwNnds6i84Va8du', N'doctorC@gmail.com', N'DaNang', 0, 1, CAST(N'2023-09-28T04:20:55.677' AS DateTime), N'https://firebasestorage.googleapis.com/v0/b/projecthk4chap2.appspot.com/o/doctors%2Fnull%2Fdoctor05.jpg?alt=media', N'DOCTOR')
INSERT [dbo].[doctor] ([id], [name], [username], [password], [email], [address], [gender], [type_doctor_id], [create_at], [image], [role]) VALUES (4, N'Tran Van B', NULL, N'$2a$10$MgXZiafX3zbH78VrvI6cPevvP04odHlRf9lKSKg5YaoUn0HzY9NqG', N'doctorD@gmail.com', N'hcm city', 1, 2, CAST(N'2023-09-27T23:32:15.420' AS DateTime), N'https://firebasestorage.googleapis.com/v0/b/projecthk4chap2.appspot.com/o/doctors%2Fnull%2Fdoctor02.jpg?alt=media', N'DOCTOR')
INSERT [dbo].[doctor] ([id], [name], [username], [password], [email], [address], [gender], [type_doctor_id], [create_at], [image], [role]) VALUES (5, N'Thuong', NULL, N'$2a$10$e.st/oQkAhDL538l5hcU6uS8g5u6RpG0vPKfjwIAzfUOBD9ptKyNy', N'doctorE@gmail.com', N'VietNam', 1, 2, CAST(N'2023-09-28T04:20:55.677' AS DateTime), N'https://firebasestorage.googleapis.com/v0/b/projecthk4chap2.appspot.com/o/doctors%2Fnull%2Fdoctor06.jpg?alt=media', N'DOCTOR')
SET IDENTITY_INSERT [dbo].[doctor] OFF
GO
SET IDENTITY_INSERT [dbo].[donvi] ON

INSERT [dbo].[donvi] ([id], [name]) VALUES (1, N'first don vi')
SET IDENTITY_INSERT [dbo].[donvi] OFF
GO
SET IDENTITY_INSERT [dbo].[feedback] ON

INSERT [dbo].[feedback] ([id], [title], [content], [email], [status], [create_at], [phone]) VALUES (1003, N'Talking to admin', N'Hello Admin', N'hoangdeptraibodoiqua4321@gmail.com', 1, CAST(N'2023-09-30T21:29:28.170' AS DateTime), N'0123456789')
SET IDENTITY_INSERT [dbo].[feedback] OFF
GO
SET IDENTITY_INSERT [dbo].[lichlamviec] ON

INSERT [dbo].[lichlamviec] ([id], [thu], [date], [doctor_id], [create_at], [starttime], [endtime]) VALUES (2, N'Friday', CAST(N'2023-09-29' AS Date), 1, CAST(N'2023-09-27T17:23:48.223' AS DateTime), CAST(N'12:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[lichlamviec] ([id], [thu], [date], [doctor_id], [create_at], [starttime], [endtime]) VALUES (3, N'Friday', CAST(N'2023-09-29' AS Date), 2, CAST(N'2023-09-28T03:26:45.907' AS DateTime), CAST(N'07:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[lichlamviec] ([id], [thu], [date], [doctor_id], [create_at], [starttime], [endtime]) VALUES (4, N'Saturday', CAST(N'2023-09-30' AS Date), 2, CAST(N'2023-09-28T03:27:02.240' AS DateTime), CAST(N'07:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[lichlamviec] ([id], [thu], [date], [doctor_id], [create_at], [starttime], [endtime]) VALUES (5, N'Saturday', CAST(N'2023-09-30' AS Date), 3, CAST(N'2023-09-28T03:27:41.293' AS DateTime), CAST(N'12:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[lichlamviec] ([id], [thu], [date], [doctor_id], [create_at], [starttime], [endtime]) VALUES (1002, N'Sunday', CAST(N'2023-10-01' AS Date), 3, CAST(N'2023-09-28T10:49:50.660' AS DateTime), CAST(N'12:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[lichlamviec] ([id], [thu], [date], [doctor_id], [create_at], [starttime], [endtime]) VALUES (1003, N'Monday', CAST(N'2023-10-02' AS Date), 4, CAST(N'2023-09-28T10:50:22.730' AS DateTime), CAST(N'17:00:00' AS Time), CAST(N'21:00:00' AS Time))
INSERT [dbo].[lichlamviec] ([id], [thu], [date], [doctor_id], [create_at], [starttime], [endtime]) VALUES (1005, N'Tuesday', CAST(N'2023-10-03' AS Date), 1, CAST(N'2023-09-28T12:34:09.870' AS DateTime), CAST(N'07:00:00' AS Time), CAST(N'12:00:00' AS Time))
SET IDENTITY_INSERT [dbo].[lichlamviec] OFF
GO
SET IDENTITY_INSERT [dbo].[news] ON

INSERT [dbo].[news] ([id], [title], [content], [status], [create_at], [image]) VALUES (1, N'First title', N'Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line.', 0, CAST(N'2023-09-30T20:08:10.643' AS DateTime), N'https://firebasestorage.googleapis.com/v0/b/projecthk4chap2.appspot.com/o/news%2Fnull%2Fclinic02.jpg?alt=media')
INSERT [dbo].[news] ([id], [title], [content], [status], [create_at], [image]) VALUES (2, N'Second title', N'Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life.', 0, CAST(N'2023-09-28T02:14:56.183' AS DateTime), N'https://firebasestorage.googleapis.com/v0/b/projecthk4chap2.appspot.com/o/news%2Fnull%2Fclinic03.jpg?alt=media')
INSERT [dbo].[news] ([id], [title], [content], [status], [create_at], [image]) VALUES (4, N'Third title', N'Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line.', 1, CAST(N'2023-09-28T02:15:24.337' AS DateTime), N'https://firebasestorage.googleapis.com/v0/b/projecthk4chap2.appspot.com/o/news%2Fnull%2Fclinic06.jpg?alt=media')
INSERT [dbo].[news] ([id], [title], [content], [status], [create_at], [image]) VALUES (5, N'Four title', N'Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life.', 1, CAST(N'2023-09-28T02:16:00.810' AS DateTime), N'https://firebasestorage.googleapis.com/v0/b/projecthk4chap2.appspot.com/o/news%2Fnull%2Fclinic08.jpg?alt=media')
SET IDENTITY_INSERT [dbo].[news] OFF
GO
SET IDENTITY_INSERT [dbo].[patient] ON

INSERT [dbo].[patient] ([id], [password], [name], [email], [address], [phone], [image], [role], [gender], [dob]) VALUES (1, N'$2a$10$uIvAx4MBM5EA6hnY5P4Ky.T4QEiKX0qbPTm.6dcCpGRQaKiezb7MS', N'Thuong patient', N'patientA@gmail.com', N'SaiGon', N'0123456789', NULL, N'PATIENT', NULL, NULL)
SET IDENTITY_INSERT [dbo].[patient] OFF
GO
SET IDENTITY_INSERT [dbo].[rating] ON

INSERT [dbo].[rating] ([id], [doctor_id], [patient_id], [rating], [comment], [create_at]) VALUES (1, 1, 1, CAST(5.0 AS Decimal(2, 1)), N'nice', CAST(N'2023-09-30T22:43:25.313' AS DateTime))
SET IDENTITY_INSERT [dbo].[rating] OFF
GO
SET IDENTITY_INSERT [dbo].[thuoc] ON

INSERT [dbo].[thuoc] ([id], [name], [composition], [quantity], [price], [typethuoc_id], [create_at], [manufacturing_date], [expiry_date], [company_id], [donvi_id]) VALUES (1, N'first thuoc', N'NaCl', 3, 300, 1, NULL, CAST(N'2023-09-20' AS Date), CAST(N'2023-09-30' AS Date), 1, 1)
INSERT [dbo].[thuoc] ([id], [name], [composition], [quantity], [price], [typethuoc_id], [create_at], [manufacturing_date], [expiry_date], [company_id], [donvi_id]) VALUES (2, N'second thuoc', N'KCL', 10, 300, 1, CAST(N'2023-09-28T10:24:44.150' AS DateTime), CAST(N'2023-09-20' AS Date), CAST(N'2023-09-30' AS Date), 1, 1)
SET IDENTITY_INSERT [dbo].[thuoc] OFF
GO
SET IDENTITY_INSERT [dbo].[type_doctor] ON

INSERT [dbo].[type_doctor] ([id], [name]) VALUES (1, N'type doctor A')
INSERT [dbo].[type_doctor] ([id], [name]) VALUES (2, N'type doctor B')
INSERT [dbo].[type_doctor] ([id], [name]) VALUES (3, N'type doctor C')
SET IDENTITY_INSERT [dbo].[type_doctor] OFF
GO
SET IDENTITY_INSERT [dbo].[typethuoc] ON

INSERT [dbo].[typethuoc] ([id], [name]) VALUES (1, N'type thuoc A')
SET IDENTITY_INSERT [dbo].[typethuoc] OFF
GO
ALTER TABLE [dbo].[appointment]  WITH CHECK ADD  CONSTRAINT [FK_appointment_doctor] FOREIGN KEY([doctor_id])
REFERENCES [dbo].[doctor] ([id])
GO
ALTER TABLE [dbo].[appointment] CHECK CONSTRAINT [FK_appointment_doctor]
GO
ALTER TABLE [dbo].[appointment]  WITH CHECK ADD  CONSTRAINT [FK_appointment_patient] FOREIGN KEY([patient_id])
REFERENCES [dbo].[patient] ([id])
GO
ALTER TABLE [dbo].[appointment] CHECK CONSTRAINT [FK_appointment_patient]
GO
ALTER TABLE [dbo].[doctor]  WITH CHECK ADD  CONSTRAINT [FK_doctor_type_doctor] FOREIGN KEY([type_doctor_id])
REFERENCES [dbo].[type_doctor] ([id])
GO
ALTER TABLE [dbo].[doctor] CHECK CONSTRAINT [FK_doctor_type_doctor]
GO
ALTER TABLE [dbo].[donthuoc]  WITH CHECK ADD  CONSTRAINT [FK_donthuoc_casher] FOREIGN KEY([casher_id])
REFERENCES [dbo].[casher] ([id])
GO
ALTER TABLE [dbo].[donthuoc] CHECK CONSTRAINT [FK_donthuoc_casher]
GO
ALTER TABLE [dbo].[donthuoc]  WITH CHECK ADD  CONSTRAINT [FK_donthuoc_toathuoc] FOREIGN KEY([toathuoc_id])
REFERENCES [dbo].[toathuoc] ([id])
GO
ALTER TABLE [dbo].[donthuoc] CHECK CONSTRAINT [FK_donthuoc_toathuoc]
GO
ALTER TABLE [dbo].[donthuoc_details]  WITH CHECK ADD  CONSTRAINT [FK_donthuoc_details_donthuoc] FOREIGN KEY([donthuoc_id])
REFERENCES [dbo].[donthuoc] ([id])
GO
ALTER TABLE [dbo].[donthuoc_details] CHECK CONSTRAINT [FK_donthuoc_details_donthuoc]
GO
ALTER TABLE [dbo].[donthuoc_details]  WITH CHECK ADD  CONSTRAINT [FK_donthuoc_details_thuoc] FOREIGN KEY([thuoc_id])
REFERENCES [dbo].[thuoc] ([id])
GO
ALTER TABLE [dbo].[donthuoc_details] CHECK CONSTRAINT [FK_donthuoc_details_thuoc]
GO
ALTER TABLE [dbo].[lichlamviec]  WITH CHECK ADD  CONSTRAINT [FK_lichlamviec_doctor] FOREIGN KEY([doctor_id])
REFERENCES [dbo].[doctor] ([id])
GO
ALTER TABLE [dbo].[lichlamviec] CHECK CONSTRAINT [FK_lichlamviec_doctor]
GO
ALTER TABLE [dbo].[rating]  WITH CHECK ADD  CONSTRAINT [FK_rating_doctor] FOREIGN KEY([doctor_id])
REFERENCES [dbo].[doctor] ([id])
GO
ALTER TABLE [dbo].[rating] CHECK CONSTRAINT [FK_rating_doctor]
GO
ALTER TABLE [dbo].[rating]  WITH CHECK ADD  CONSTRAINT [FK_rating_patient] FOREIGN KEY([patient_id])
REFERENCES [dbo].[patient] ([id])
GO
ALTER TABLE [dbo].[rating] CHECK CONSTRAINT [FK_rating_patient]
GO
ALTER TABLE [dbo].[taophieukham]  WITH CHECK ADD  CONSTRAINT [FK_taophieukham_casher] FOREIGN KEY([casher_id])
REFERENCES [dbo].[casher] ([id])
GO
ALTER TABLE [dbo].[taophieukham] CHECK CONSTRAINT [FK_taophieukham_casher]
GO
ALTER TABLE [dbo].[taophieukham]  WITH CHECK ADD  CONSTRAINT [FK_taophieukham_type_doctor] FOREIGN KEY([type_doctor_id])
REFERENCES [dbo].[type_doctor] ([id])
GO
ALTER TABLE [dbo].[taophieukham] CHECK CONSTRAINT [FK_taophieukham_type_doctor]
GO
ALTER TABLE [dbo].[thuoc]  WITH CHECK ADD  CONSTRAINT [FK_thuoc_company] FOREIGN KEY([company_id])
REFERENCES [dbo].[company] ([id])
GO
ALTER TABLE [dbo].[thuoc] CHECK CONSTRAINT [FK_thuoc_company]
GO
ALTER TABLE [dbo].[thuoc]  WITH CHECK ADD  CONSTRAINT [FK_thuoc_donvi] FOREIGN KEY([donvi_id])
REFERENCES [dbo].[donvi] ([id])
GO
ALTER TABLE [dbo].[thuoc] CHECK CONSTRAINT [FK_thuoc_donvi]
GO
ALTER TABLE [dbo].[thuoc]  WITH CHECK ADD  CONSTRAINT [FK_thuoc_typethuoc] FOREIGN KEY([typethuoc_id])
REFERENCES [dbo].[typethuoc] ([id])
GO
ALTER TABLE [dbo].[thuoc] CHECK CONSTRAINT [FK_thuoc_typethuoc]
GO
ALTER TABLE [dbo].[toathuoc]  WITH CHECK ADD  CONSTRAINT [FK_toathuoc_doctor] FOREIGN KEY([doctor_id])
REFERENCES [dbo].[doctor] ([id])
GO
ALTER TABLE [dbo].[toathuoc] CHECK CONSTRAINT [FK_toathuoc_doctor]
GO
ALTER TABLE [dbo].[toathuoc]  WITH CHECK ADD  CONSTRAINT [FK_toathuoc_taophieukham] FOREIGN KEY([taophieukham_id])
REFERENCES [dbo].[taophieukham] ([id])
GO
ALTER TABLE [dbo].[toathuoc] CHECK CONSTRAINT [FK_toathuoc_taophieukham]
GO
ALTER TABLE [dbo].[toathuoc_details]  WITH CHECK ADD  CONSTRAINT [FK_toathuoc_details_thuoc] FOREIGN KEY([thuoc_id])
REFERENCES [dbo].[thuoc] ([id])
GO
ALTER TABLE [dbo].[toathuoc_details] CHECK CONSTRAINT [FK_toathuoc_details_thuoc]
GO
ALTER TABLE [dbo].[toathuoc_details]  WITH CHECK ADD  CONSTRAINT [FK_toathuoc_details_toathuoc] FOREIGN KEY([toathuoc_id])
REFERENCES [dbo].[toathuoc] ([id])
GO
ALTER TABLE [dbo].[toathuoc_details] CHECK CONSTRAINT [FK_toathuoc_details_toathuoc]
GO
