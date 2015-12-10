package com.takeaway;

import java.sql.*;
import java.util.*;


public class store_Bean {
	private Connection conn;
	public store_Bean(){}
	public boolean get_allstore_info(ArrayList<store_Info> data,double lng,double lat,String type,String order,int pageon)
	{
		conn = DBconn.GetConnection();
		try
		{
			String sql = "select "+store_Info.dataTable_store_Name+".id,shop_name ,first_type,second_type,shop_owner,shop_address,shop_description,shop_phone,shop_license ,shop_notice,discount,score,sell_num,price_tosend,ave_sendtime from "
					+store_Info.dataTable_application_name+","+store_Info.dataTable_store_Name
					+" where "+ store_Info.dataTable_store_Name+".username = "
					+store_Info.dataTable_application_name+".username "
					+" and  state='1' and abs(longitude-'"
					+lng+"')<=0.01 and abs(latitude-'"
					+lat+"')<=0.01 ";
			if(type!="")	
			{
					sql+="and first_type = '";
					sql+=type;
					sql+="'";
			}
			if(order!="")
			{
				sql+=" order by ";
				sql+=order;
			}
			if(order.equals("sell_num")||order.equals("score"))
			{
				sql+=" desc ";
			}
			if(pageon!=-1)
			{
				pageon=pageon*store_Info.pur_page;
				sql+="limit ";
				sql+=pageon;
				sql+=",";
				sql+=store_Info.pur_page;
			}
			System.out.println(sql);
			Statement st = conn.createStatement();
			ResultSet rs =st.executeQuery(sql);
			while(rs.next())
			{
				store_Info elem= GetDataFromResultSet_shoplist(rs);
				data.add(elem);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("关闭连接失败"+e.getMessage());
					return false;
				}
			}
		}
		return true;
	}
	public boolean check_application_repeat(String username)
	{
		conn = DBconn.GetConnection();
		try
		{
			String sql = "select * from "+store_Info.dataTable_application_name + " where username ='"+username+"'";
			System.out.println(sql);
			Statement st =conn.createStatement();
			ResultSet rs =st.executeQuery(sql);
			if(rs.next())
				return true;
			else 
				return false;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("关闭连接失败"+e.getMessage());
					return false;
				}
			}
		}
	}
	public boolean get_application_info(ArrayList<store_Info> data)
	{
		conn = DBconn.GetConnection();
		try
		{
			String sql = "select "+store_Info.dataTable_application_name+".id,shop_name ,first_type,second_type,shop_owner,shop_address,shop_description,shop_phone,shop_license,submit_time from "
						+store_Info.dataTable_store_Name+","+store_Info.dataTable_application_name 
						+" where "+ store_Info.dataTable_store_Name+".username = "
						+store_Info.dataTable_application_name+".username "
						+" and  state='2' order by submit_time limit 0,8";
			System.out.println(sql);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				store_Info elem= GetDataFromResultSet_review(rs);
				data.add(elem);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("关闭连接失败"+e.getMessage());
					return false ;
				}
			}
		}
		return true;
	}
	public boolean set_application(String state,String  id)
	{
		conn = DBconn.GetConnection();
		try
		{
			String sql = "update "+store_Info.dataTable_application_name
					+" set state = '"+state+"' where id = '"+id+"'";
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("关闭连接失败"+e.getMessage());
					return false ;
				}
			}
		}
		return true;
	}
	public boolean submit_application(String username)
	{
		conn = DBconn.GetConnection();
		try
		{
			StringBuffer sql = new StringBuffer();
			sql.append("insert into ")
			.append(store_Info.dataTable_application_name)
			.append("(username,submit_time )")
			.append("values(?,?)");
			PreparedStatement st = conn.prepareStatement(sql.toString());
			st.setString(1, username);
			st.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
			st.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("关闭连接失败"+e.getMessage());
					return false ;
				}
			}
		}
		return true;
	}
	public boolean submit_store_info(store_Info info)
	{
		conn = DBconn.GetConnection();
		try	
		{
			StringBuffer sBuffer =new StringBuffer();
			sBuffer.append("insert into ")
			.append(store_Info.dataTable_store_Name)
			.append("(username , shop_name , shop_address , shop_phone ,first_type , second_type , shop_owner ,longitude ,latitude ,shop_license ,shop_description )")
			.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement st=conn.prepareStatement(sBuffer.toString());
			st.setString(1, info.getUsername());
			st.setString(2, info.getShop_name());
			st.setString(3, info.getShop_address());
			st.setString(4, info.getShop_phone());
			st.setString(5, info.getFirst_type());
			st.setString(6, info.getSecond_type());
			st.setString(7, info.getShop_owner());
			st.setDouble(8, info.getLongitude());
			st.setDouble(9, info.getLatitude());
			st.setString(10, info.getShop_license());
			st.setString(11, info.getShop_description());
			System.out.println(sBuffer);
			st.executeUpdate();
			System.out.println("插入成功");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("关闭连接失败"+e.getMessage());
					return false ;
				}
			}
		}
		return true;
	}
	public boolean get_secondtype(ArrayList<String> data , String first)
	{
		conn = DBconn.GetConnection();
		data.clear();
		try
		{
			String sql = "select  second_type  from "+store_Info.dataTable_type_Name
					+" where first_type = '"+first+"'";
			System.out.println(sql);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				String type = rs.getString("second_type");
				data.add(type);
			}
			System.out.println(data.toString());
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("关闭连接失败"+e.getMessage());
					return false ;
				}
			}
		}
	}
	public boolean get_firsttype(ArrayList<String> data )
	{
		conn = DBconn.GetConnection();
		data.clear();
		try
		{
			String sql = "select distinct first_type  from "+store_Info.dataTable_type_Name;
			System.out.println(sql);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				String type = rs.getString("first_type");
				data.add(type);
			}
			System.out.println(data.toString());
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("关闭连接失败"+e.getMessage());
					return false ;
				}
			}
		}
	}
	public store_Info GetDataFromResultSet_review(ResultSet rs)
	{
		store_Info info = new store_Info();
		try
		{
			info.setId(rs.getInt("id"));
			info.setFirst_type(rs.getString("first_type"));
			info.setSecond_type(rs.getString("second_type"));
			info.setShop_address(rs.getString("shop_address"));
			info.setShop_description(rs.getString("shop_description"));
			info.setShop_license(rs.getString("shop_license"));
			info.setShop_name(rs.getString("shop_name"));
			info.setShop_owner(rs.getString("shop_owner"));
			info.setShop_phone(rs.getString("shop_phone"));
			info.setSubmit_date(rs.getDate("submit_time"));
			//System.out.println(rs.getString("shop_notice"));
			
		}
		catch(SQLException e)
		{
			System.out.printf( "数据库查询失败\n" + e.getMessage()  );
		}
		return info;
	}
	public store_Info GetDataFromResultSet_shoplist(ResultSet rs)
	{
		store_Info info = new store_Info();
		try
		{
			info.setId(rs.getInt("id"));
			info.setFirst_type(rs.getString("first_type"));
			info.setSecond_type(rs.getString("second_type"));
			info.setShop_address(rs.getString("shop_address"));
			info.setShop_description(rs.getString("shop_description"));
			info.setShop_license(rs.getString("shop_license"));
			info.setShop_name(rs.getString("shop_name"));
			info.setShop_owner(rs.getString("shop_owner"));
			info.setShop_phone(rs.getString("shop_phone"));
			info.setShop_notice(rs.getString("shop_notice"));
			info.setScore(rs.getDouble("score"));
			info.setSell_num(rs.getInt("sell_num"));
			info.setPrice_tosend(rs.getDouble("price_tosend"));
			info.setAve_sendtime(rs.getInt("ave_sendtime"));
			info.setDiscount(rs.getString("discount"));
		}
		catch(SQLException e)
		{
			System.out.printf( "数据库查询失败\n" + e.getMessage()  );
		}
		return info;
	}
	/*public boolean get_secondtype(ArrayList<String> data )
	{
		try
		{
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("关闭连接失败"+e.getMessage());
					return false ;
				}
			}
		}
	}*/
}
