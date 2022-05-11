//
//  KanjiResourceScreen.swift
//  iosApp
//
//  Created by Suraj GP on 09/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct KanjiResourceScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        
        ZStack {
            
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                
                AppTopBar(navigateUpIcon: "arrow.backward", title: "Kanji") {
                    self.presentationMode.wrappedValue.dismiss()
                }
                    .padding()
                
                VStack(alignment: .leading) {
                    Text("Grades")
                        .font(.title)
                        .fontWeight(.semibold)
                        .foregroundColor(Color.Neomorph.text)
                    
                    let columns = [
                        GridItem(.adaptive(minimum: 72), spacing: 24)
                    ]
                    
                    Text("小学校（1-6）")
                        .font(.title2)
                        .fontWeight(.semibold)
                        .foregroundColor(Color.Neomorph.text)
                    
                    LazyVGrid(columns: columns, spacing: 24) {
                        ForEach(1...6, id: \.self) { grade in
                            NavigationLink {
                                KanjiListScreen(query: KanjiQueryGrade(grade: Int32(grade)))
                            } label: {
                                KanjiGradeItem(grade: "\(grade)")
                            }
                        }
                    }
                    .padding(.vertical)
                    
                    Text("中学校（7-9）")
                        .font(.title2)
                        .fontWeight(.semibold)
                        .foregroundColor(Color.Neomorph.text)
                    
                    LazyVGrid(columns: columns) {
                        ForEach(7...9, id: \.self) { grade in
                            NavigationLink {
                                KanjiListScreen(query: KanjiQueryGrade(grade: Int32(grade)))
                            } label: {
                                KanjiGradeItem(grade: "\(grade)")
                            }
                        }
                    }
                    .padding(.vertical)
                    
                    NavigationLink {
                        KanjiListScreen(query: KanjiQueryAllSchool.init())
                    } label: {
                        AllSchoolItem()
                    }
                }
                .padding()
                
                Spacer()
            }
        }
        .navigationBarBackButtonHidden(true)
        .navigationBarHidden(true)
    }
}
