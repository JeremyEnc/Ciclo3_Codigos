library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Alarma is
    Port ( clk, fx, cont4, boton : in  STD_LOGIC;
           reset, inc, activa, alarm : out  STD_LOGIC);
end Alarma;

architecture Behavioral of Alarma is

TYPE estados is(A,B,C,D,E,F,G,H,I,J,K,L);
signal estAct, estFut: estados;

begin

	process(estAct, fx, cont4, boton) 
	begin
		
		case estAct is
			when A =>
				reset <= '1'; inc <= '0';activa <= '0'; alarm <= '0';
				estFut <= B;
			when B =>
				reset <= '0'; inc <= '0';activa <= '0'; alarm <= '0';
				if(boton = '1')then
					estFut <= C;
				else
					estFut <= B;
				end if;
			when C =>
				reset <= '0'; inc <= '0';activa <= '0'; alarm <= '0';
				if(fx = '1')then
					estFut <= D;
				else
					estFut <= H;
				end if;
			when D =>
				reset <= '0'; inc <= '0';activa <= '0'; alarm <= '0';
				if(boton = '1')then
					estFut <= D;
				else
					estFut <= E;
				end if;
			when E =>
				reset <= '0'; inc <= '0';activa <= '0'; alarm <= '0';
				if(cont4 = '1')then
					estFut <= G;
				else
					estFut <= f;
				end if;
			when F =>
				reset <= '0'; inc <= '1';activa <= '0'; alarm <= '0';
				estFut <= B;
			when G =>
				reset <= '0'; inc <= '0';activa <= '1'; alarm <= '0';
				estFut <= A;
			when H =>
				reset <= '0'; inc <= '0';activa <= '0'; alarm <= '0';
				if(boton = '1')then
					estFut <= H;
				else
					estFut <= i;
				end if;
			when I =>
				reset <= '0'; inc <= '0';activa <= '0'; alarm <= '0';
				if(cont4 = '1')then
					estFut <= L;
				else
					estFut <= J;
				end if;
			when J =>
				reset <= '0'; inc <= '1';activa <= '0'; alarm <= '0';
				estFut <= K;
			when K =>
				reset <= '0'; inc <= '0';activa <= '0'; alarm <= '0';
				if(boton = '1')then
					estFut <= H;
				else
					estFut <= K;
				end if;
			when L =>
				reset <= '0'; inc <= '1';activa <= '0'; alarm <= '1';
				estFut <= A;
			end case;
		end process;

		process(clk)
		begin
			if(clk'event and clk = '1')then
				estAct <= estFut;
			end if;
			report "estado: " & estados'image(estAct);
		end process;
	
end Behavioral;

