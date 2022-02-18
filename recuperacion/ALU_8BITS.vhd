----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:41:49 01/23/2022 
-- Design Name: 
-- Module Name:    ALU_8BITS - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity ALU_8BITS is
  port (
         a_i      : in std_logic_vector(7 downto 0);
         b_i      : in std_logic_vector(7 downto 0);
         opcode_i : in std_logic_vector(1 downto 0);
         d_o      : out std_logic_vector(7 downto 0)
  );
end entity;
architecture RTL of ALU_8BITS is
begin
  process(a_i,b_i,opcode_i)
  begin
    case opcode_i is
      when "00" =>
        d_o  <= std_logic_vector(unsigned(a_i) + unsigned(b_i));
      when "01" =>
        d_o  <= std_logic_vector(unsigned(a_i) - unsigned(b_i));
      when "10" =>
        d_o <= a_i and b_i;
      when others =>
        d_o <= a_i or b_i;
    end case;
  end process;
end architecture;

